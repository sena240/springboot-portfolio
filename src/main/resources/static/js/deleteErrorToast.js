const toastDeleteError = document.getElementById('toastDeleteError');


document.addEventListener('DOMContentLoaded', () => {
   const urlParams = new URLSearchParams(window.location.search);
   if (urlParams.get('deleteToast') === 'false') {
      const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastDeleteError);
      toastBootstrap.show();
   }
});