const toastAdd = document.getElementById('toastAdd');


document.addEventListener('DOMContentLoaded', () => {
   const urlParams = new URLSearchParams(window.location.search);
   if (urlParams.get('addToast') === 'true') {
      const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastAdd);
      toastBootstrap.show();
   }
});
