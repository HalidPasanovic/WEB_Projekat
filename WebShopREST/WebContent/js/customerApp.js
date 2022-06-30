const Homepage = { template: '<homepage></homepage>' }
const Profile = { template: '<viewProfile></viewProfile>' }


const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Homepage},
		{ path: '/profile', component: Profile}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Customer'
});