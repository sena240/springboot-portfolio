let deleteProjectModal = document.getElementById('deleteProjectConfirmModal');
let deleteProjectBtn = document.getElementById('confirmProjectDelete');
let projectIdToDelete;

// モーダルが表示される前のイベント
deleteProjectModal.addEventListener('show.bs.modal', function(event) {
    let deleteProjectButton = event.relatedTarget;

    // th:data-member-idを取得
    projectIdToDelete = deleteProjectButton.getAttribute('data-project-id');
});

// モーダルの削除ボタンクリック時のイベント
deleteProjectBtn.addEventListener('click', function() {
    window.location.href = `/project/delete/${projectIdToDelete}`;
});
