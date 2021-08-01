(() => {

	const $title = document.getElementById('titleSearch').addEventListener('input', (e) => titleSearch(e));

	const titleSearch = (e) => {
		let article = document.getElementById('article');
		let title = e.target.value;
		fetch("/titleSearch?title=" + title, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			article.outerHTML = body;
		})
			.catch((reason) => {
				console.log(reason);
			});
	}

})();