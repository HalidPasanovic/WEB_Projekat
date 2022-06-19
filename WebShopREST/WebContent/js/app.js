const CreateFacility = { template: '<create-facility></create-facility>' }
const Product = { template: '<edit-product></edit-product>' }
const Products = { template: '<products></products>' }
const Login = { template: '<login></login>'}
const Create = { template: '<create></create>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Login},
	    { path: '/products/:id', component: Product},
	    { path: '/products', component: Products},
	    { path: '/createFacility' ,component: CreateFacility},
	    { path: '/create', component: Create}
	  ]
});

var app = new Vue({
	router,
	el: '#WebShop'
});