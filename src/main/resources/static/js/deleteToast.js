const toastDelete = document.getElementById('toastDelete');


document.addEventListener('DOMContentLoaded', () => {
   const urlParams = new URLSearchParams(window.location.search);
   if (urlParams.get('deleteToast') === 'true') {
      const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastDelete);
      toastBootstrap.show();
   }
});