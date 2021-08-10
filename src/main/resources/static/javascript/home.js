(() => {
	const $sortTab = document.getElementById('sortTab');
	console.log($sortTab);
 	const $sort =$sortTab.querySelectorAll('[data-sort]');
 	console.log($sort);
  	let index = 0;
  		while(index < $sort.length){
  			$sort[index].addEventListener('click',(e) => order(e));
  			index++;
  		}
  		
  const order =(e) =>{
  	const $this = e.target;
  	const targetVal = $this.dataset.sort;
  	console.log(targetVal);

  //ここからAjax通信
  let article = document.getElementById('article');
  fetch("/sortOrder?sortName=" + targetVal,{
  method:"get"}).then(response => {
  return response.text();
  }).then(body => {
  console.log('aa',body);
  article.innerHTML = body;
  
  });
  }

 
})();