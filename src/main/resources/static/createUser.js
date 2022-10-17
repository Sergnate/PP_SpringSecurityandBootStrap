// Create new user

const username = document.getElementById('username')
const userGender = document.getElementById('userGender')
const userNickname = document.getElementById('userNickname')
const password = document.getElementById('password')
const role = document.getElementById('role')
const addForm = document.querySelector('.addForm')

addForm.addEventListener('submit', e => {
    e.preventDefault();

    console.log('Form submitted');
    console.log(role.value)

    fetch("api", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user = {
            username: username.value,
            userGender: userGender.value,
            userNickname: userNickname.value,
            password: password.value,
            roles: role.value
        })
    }).then(()=> getUsers())
        .then(() => addForm.reset())

   return show('showUsers','addUser')
})



