const editId = document.getElementById('edId')
const editUsername = document.getElementById('edUsername')
const editUserGender = document.getElementById('edUserGender')
const editUserNickname = document.getElementById('edUserNickname')
const editPassword = document.getElementById('edPassword')
const editRole = document.getElementById('editRole')
const editModalForm = document.querySelector('.editModalForm')

editModalForm.addEventListener('submit', e => {
    e.preventDefault();

    fetch("api", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user = {
            id: editId.value,
            username: editUsername.value,
            userGender:editUserGender.value,
            userNickname: editUserNickname.value,
            password: editPassword.value,
            roles: editRole.value
        })
    }).then(()=> getUsers())
    $("#editModal").modal("hide");
    editModalForm.reset();

})
