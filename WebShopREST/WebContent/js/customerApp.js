const Homepage = { template: '<homepage></homepage>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Homepage}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Customer'
});