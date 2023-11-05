let deleteMeetingModal = document.getElementById('deleteMeetingConfirmModal');
let deleteMeetingBtn = document.getElementById('confirmMeetingDelete');
let meetingIdToDelete;

// モーダルが表示される前のイベント
deleteMeetingModal.addEventListener('show.bs.modal', function(event) {
    let deleteMeetingButton = event.relatedTarget;

    // th:data-meeting-idを取得
    meetingIdToDelete = deleteMeetingButton.getAttribute('data-meeting-id');
});

// モーダルの削除ボタンクリック時のイベント
deleteMeetingBtn.addEventListener('click', function() {
    window.location.href = `/meeting/delete/${meetingIdToDelete}`;
});