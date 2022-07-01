const ViewFacility = { template: '<view-facility></view-facility>' }
const Create = {template: '<create></create>'}
const Facility = {template: '<facillity></facillity>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: ViewFacility},
		{ path: '/create/:id', component: Create},
		{ path: '/facility/:id', component: Facility}
	  ]
});

var app = new Vue({
	router,
	el: '#Administrator'
});