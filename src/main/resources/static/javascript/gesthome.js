(() => {
const category = document.getElementById('category');
const s = document.getElementById('search').addEventListener('input',(e) => cc(e));
console.log(s);
const $category = category.querySelectorAll('[data-category]');
console.log($category);
let index = 0;
while(index < $category.length){
$category[index].addEventListener('click',(e) => search(e));
index++;
}
const search = (e) =>{
const $this = e.target;
  	const targetVal = $this.dataset.category;
  	console.log(targetVal);

 let a = document.getElementById('tab1');
 console.log(a);
 fetch("/category?searchid=" + targetVal,{
  method:"get"}).then(response => {
  return response.text();
  }).then(body => {
  console.log('aa',body);
  a.outerHTML = body;
  count();
  });

}
const count = () =>{
  let mm = document.getElementById('tab1');
  console.log(mm);
  }
  
  const cc = (e) =>{
  let a = document.getElementById('tab1');
  let text = e.target.value;
  console.log(text);
  fetch("/cc?text=" + text,{
  method:"get"}).then(response => {
  return response.text();
  }).then(body => {
  console.log('aa',body);
  console.log('テキスト');
  a.outerHTML = body;
  });
  }
  
  const button = document.getElementById('button');
  console.log(button);
  button.addEventListener('click',(e) => comment(e));
  const comment = (e) =>{
  let textarea =document.getElementById('text');
  let text =  textarea.value;
  let  hidden =document.getElementById('hidden').value;
console.log(hidden);
  const c = document.getElementById('comments');
  
  fetch("/comment?text=" + text + "&hidden=" + hidden,{
  method:"get"}).then(response => {
  return response.text();
  }).then(body => {
  console.log('aa',body);
  console.log('テキスト');
  c.outerHTML = body;
  textarea.value = '';
  console.log(text);
  });
  }
  
  
})();