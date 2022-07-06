const Homepage = { template: '<homepage></homepage>' }
const Profile = { template: '<viewProfile></viewProfile>' }
const Trainings = { template: '<trainings></trainings>' }
const Membership = { template: '<membership></membership>' }
const ViewFacility = { template: '<viewFacility></viewFacility>' }
const CreateComment = { template: '<createComment></createComment>' }
const CreateAppointment = { template: '<createAppointment></createAppointment>' }


const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Homepage},
		{ path: '/profile', component: Profile},
		{ path: '/trainings', component: Trainings},
		{ path: '/membership', component: Membership},
		{ path: '/viewFacility/:id', component: ViewFacility},
		{ path: '/createComment/:id', component: CreateComment},
		{ path: '/createAppointment/:id', component: CreateAppointment}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Customer'
});