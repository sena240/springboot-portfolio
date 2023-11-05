const editTrigger = document.getElementById('confirmEdit');

if (editTrigger) {
	editTrigger.addEventListener('click', function() {
		document.querySelector('form').submit();
	})
}