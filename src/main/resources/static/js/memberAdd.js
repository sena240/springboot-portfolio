const addTrigger = document.getElementById('confirmAdd');

if (addTrigger) {
	addTrigger.addEventListener('click', function() {
		document.querySelector('form').submit();
	})
}
