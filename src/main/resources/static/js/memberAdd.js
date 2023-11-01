const trigger = document.getElementById('confirmAdd');

if (trigger) {
	trigger.addEventListener('click', function() {
		document.querySelector('form').submit();
	})
}
