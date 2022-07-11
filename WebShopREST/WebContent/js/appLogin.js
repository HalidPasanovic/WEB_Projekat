const MainLogin = { template: '<mainLogin></mainLogin>' }
const Login = { template: '<login></login>'}
const ViewFacility = { template: '<viewFacility></viewFacility>' }
const Create = {template: '<create></create>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: MainLogin},
		{ path: '/login', component: Login},
		{ path: '/viewFacility/:id', component: ViewFacility},
		{ path: '/create', component: Create}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Login'
});