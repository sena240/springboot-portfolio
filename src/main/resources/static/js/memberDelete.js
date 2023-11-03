let deleteModal = document.getElementById('deleteConfirmModal');
let deleteBtn = document.getElementById('confirmDelete');
let memberIdToDelete;

// モーダルが表示される前のイベント
deleteModal.addEventListener('show.bs.modal', function(event) {
    let deleteButton = event.relatedTarget;

    // th:data-member-idを取得
    memberIdToDelete = deleteButton.getAttribute('data-member-id');
});

// モーダルの削除ボタンクリック時のイベント
deleteBtn.addEventListener('click', function() {
    window.location.href = `/delete/${memberIdToDelete}`;
});

