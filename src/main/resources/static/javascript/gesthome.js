(() => {
	const count = () => {
		let mm = document.getElementById('tab1');
		console.log(mm);
	}

	const button = document.getElementById('button');
	console.log(button);
	button.addEventListener('click', (e) => comment(e));
	const comment = (e) => {
		let textarea = document.getElementById('text');
		let text = textarea.value;
		let hidden = document.getElementById('hidden').value;
		console.log(hidden);
		const c = document.getElementById('comments');

		fetch("/comment?text=" + text + "&hidden=" + hidden, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			console.log('aa', body);
			console.log('テキスト');
			c.outerHTML = body;
			textarea.value = '';
			console.log(text);
		});
	}

	const favorite = document.getElementById('favorite');
	console.log(favorite);
	favorite.addEventListener('click', (e) => inputFavorite(e));
	const inputFavorite = (e) => {
		console.log(e);
		let hidden = document.getElementById('hidden').value;
		fetch("/inputFavorite?hidden=" + hidden, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			window.alert(body);
			console.log('テキスト');
		});
	}

})();