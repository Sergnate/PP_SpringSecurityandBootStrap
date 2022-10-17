// show info in modal

const deleteModal = document.getElementById('deleteModal')

let button
let idFromButton

deleteModal.addEventListener('show.bs.modal', event => {
    button = event.relatedTarget
    idFromButton = button.getAttribute('data-bs-userId')
    const deleteUserName = button.getAttribute('data-bs-userName')
    const deleteUserGender = button.getAttribute('data-bs-userGender')
    const deleteUserNickname = button.getAttribute('data-bs-userNickname')

    console.log("there is the name of user")
    console.log(deleteUserName)

    const deleteModalUserId = deleteModal.querySelector('#delitId')
    const deleteModalUserName = deleteModal.querySelector('#delUsername')
    const deleteModalUserGender = deleteModal.querySelector('#delUserGender')
    const deleteModalUserNickname = deleteModal.querySelector('#delUserNickname')

    deleteModalUserId.value = idFromButton
    deleteModalUserName.value = deleteUserName
    deleteModalUserGender.value = deleteUserGender
    deleteModalUserNickname.value = deleteUserNickname
})

