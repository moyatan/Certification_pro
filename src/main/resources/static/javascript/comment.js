(() => {
const commentButton = document.getElementById('commentButton');
	console.log(button);
	button.addEventListener('click', (e) => commentRegist(e));
	const commentRegist = (e) => {
		let textarea = document.getElementById('comment');
		let text = textarea.value;
		let articleId = document.getElementById('articleId').value;
		const commentArea = document.getElementById('commentArea');

		fetch("/comment?comment=" + text + "&articleId=" + articleId, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			commentArea.outerHTML = body;
			textarea.value = '';
			console.log(text);
		});
	}
	})();