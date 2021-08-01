(() => {
const favorite = document.getElementById('favorite');
	favorite.addEventListener('click', (e) => inputFavorite(e));
	const inputFavorite = (e) => {
		console.log(e);
		let articleId = document.getElementById('articleId').value;
		fetch("/inputFavorite?articleId=" + articleId, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			window.alert(body);
		});
	}

})();