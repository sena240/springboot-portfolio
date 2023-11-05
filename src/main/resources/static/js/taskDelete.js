let deleteTaskModal = document.getElementById('deleteTaskConfirmModal');
let deleteTaskBtn = document.getElementById('confirmTaskDelete');
let taskIdToDelete;

// モーダルが表示される前のイベント
deleteTaskModal.addEventListener('show.bs.modal', function(event) {
    let deleteTaskButton = event.relatedTarget;

    // th:data-member-idを取得
    taskIdToDelete = deleteTaskButton.getAttribute('data-task-id');
});

// モーダルの削除ボタンクリック時のイベント
deleteTaskBtn.addEventListener('click', function() {
    window.location.href = `/task/delete/${taskIdToDelete}`;
});
