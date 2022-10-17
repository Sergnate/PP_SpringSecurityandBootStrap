
//  вывод в таблицу
async function getUsers() {

    fetch("/api")
        .then(res => {
            res.json().then(
                data => {
                    console.log(data);
                    if (data.length > 0) {
                        var temp = '';

                        //start for loop

                        data.forEach((user) => {
                            temp += "<tr>";
                            temp += "<td>" + user.id + "</td>";
                            temp += "<td>" + user.username + "</td>";
                            temp += "<td>" + user.userGender + "</td>";
                            temp += "<td>" + user.userNickname + "</td>";
                            temp += `<td>${user.roles.map(roles => roles.name === 'ROLE_USER' ? 'USER' : 'ADMIN')}</td>`;
                            temp += `<td><button type="button" class="btn btn-info" style="color: white" data-bs-toggle="modal" data-bs-target="#editModal" data-bs-userId=${user.id} data-bs-userName=${user.username} data-bs-userGender=${user.userGender} data-bs-userNickname=${user.userNickname} data-bs-username=${user.roles} data-bs-username=${user.password}>Редактировать</button></td>`
                            temp += `<td><button type="button" class="btn btn-danger" style="color: white" 
                                                data-bs-toggle="modal" data-bs-target="#deleteModal" 
                                                data-bs-userId=${user.id} 
                                                 data-bs-userName=${user.username}
                                                  data-bs-userGender=${user.userGender} 
                                                data-bs-userNickname=${user.userNickname}>Удалить</button></td></tr>`
                            console.log("from get user " + user.username)
                        })

                        //close for loop

                        document.getElementById('data').innerHTML = temp;

                    }
                }
            )
        })

}
