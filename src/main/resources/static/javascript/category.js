(() => {
const $categoryList = document.getElementById('categoryList');
const $category = $categoryList.querySelectorAll('[data-category]');
console.log("aaa",$category);
let index = 0;
while (index < $category.length) {
	$category[index].addEventListener('click', (e) => categorySearch(e));
	index++;
}

const categorySearch = (e) => {
		const $this = e.target;
		
		 let count = 0;
		 while(count < $category.length){
		 $category[count].style.color="black";
		 count++;
		 }
		$this.style.color = "orange";
		
		const targetVal = $this.dataset.category;

		let article = document.getElementById('article');
		console.log(article);
		
		fetch("/categorySearch?categoryId=" + targetVal, {
			method: "get"
		}).then(response => {
			return response.text();
		}).then(body => {
			console.log(body);
			article.innerHTML = body;
		});
		}

})();