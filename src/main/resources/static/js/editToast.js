const toastEdit = document.getElementById('toastEdit');


document.addEventListener('DOMContentLoaded', () => {
   const urlParams = new URLSearchParams(window.location.search);
   if (urlParams.get('editToast') === 'true') {
      const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastEdit);
      toastBootstrap.show();
   }
});