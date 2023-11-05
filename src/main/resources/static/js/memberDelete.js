let deleteMemberModal = document.getElementById('deleteMemberConfirmModal');
let deleteMemberBtn = document.getElementById('confirmMemberDelete');
let memberIdToDelete;

// モーダルが表示される前のイベント
deleteMemberModal.addEventListener('show.bs.modal', function(event) {
    let deleteMemberButton = event.relatedTarget;

    // th:data-member-idを取得
    memberIdToDelete = deleteMemberButton.getAttribute('data-member-id');
});

// モーダルの削除ボタンクリック時のイベント
deleteMemberBtn.addEventListener('click', function() {
    window.location.href = `/member/delete/${memberIdToDelete}`;
});
