// show info in modal

const editModal = document.getElementById('editModal')
const modalUserId = editModal.querySelector('#edId')
const modalUserName = editModal.querySelector('#edUsername')
const modalUserGender = editModal.querySelector('#edUserGender')
const modalUserNickname = editModal.querySelector('#edUserNickname')

editModal.addEventListener('show.bs.modal', event => {
    const button = event.relatedTarget
    const userId = button.getAttribute('data-bs-userId')
    const userName = button.getAttribute('data-bs-userName')
    const userGender = button.getAttribute('data-bs-userGender')
    const userNickname = button.getAttribute('data-bs-userNickname')

     console.log("there is the name of user")
    console.log(userName)

    modalUserId.value = userId
    modalUserName.value = userName
    modalUserGender.value = userGender
    modalUserNickname.value = userNickname

})

