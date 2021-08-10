(() => {
const favorite = document.getElementById('favorite');
	favorite.addEventListener('click', (e) => inputFavorite(e));
	const inputFavorite = (e) => {
		let articleId = document.getElementById('articleId').value;
		fetch("/inputFavorite?articleId=" + articleId, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
		if(body === ""){
		favorite.src="/images/favorite_1.png";
		}else{
			favorite.src="/images/favorite_0.png";
			}
		});
	}

})();