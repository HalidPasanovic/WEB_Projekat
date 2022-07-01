const Homepage = { template: '<homepage></homepage>' }
const Profile = { template: '<viewProfile></viewProfile>' }
const Trainings = { template: '<trainings></trainings>' }
const Membership = { template: '<membership></membership>' }
const ViewFacility = { template: '<viewFacility></viewFacility>' }


const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Homepage},
		{ path: '/profile', component: Profile},
		{ path: '/trainings', component: Trainings},
		{ path: '/membership', component: Membership},
		{ path: '/viewFacility/:id', component: ViewFacility}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Customer'
});