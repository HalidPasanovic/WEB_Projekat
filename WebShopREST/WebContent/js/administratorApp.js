const ViewFacility = { template: '<view-facility></view-facility>' }
const Create = {template: '<create></create>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: ViewFacility},
		{ path: '/create/:id', component: Create}
	  ]
});

var app = new Vue({
	router,
	el: '#Administrator'
});