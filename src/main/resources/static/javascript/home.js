(() => {
	const $sorttab = document.getElementById('sorttab');
 	const $nav =$sorttab.querySelectorAll('[data-sort]');
  	let index = 0;
  		while(index < $nav.length){
  			$nav[index].addEventListener('click',(e) => sort(e));
  			index++;
  		}
  		
  const sort =(e) =>{
  	const $this = e.target;
  	const targetVal = $this.dataset.sort;

  //ここからAjax通信
  let a = document.getElementById('tab1');
  fetch("/sort?sortid=" + targetVal,{
  method:"get"}).then(response => {
  return response.text();
  }).then(body => {
  console.log('aa',body);
  a.outerHTML = body;
  
  });
  }

 
})();