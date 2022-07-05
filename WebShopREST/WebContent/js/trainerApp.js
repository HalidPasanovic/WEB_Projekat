const Homepage = { template: '<homepage></homepage>' }
const Profile = { template: '<viewProfile></viewProfile>' }
const Trainings = { template: '<trainings></trainings>' }
const ViewFacility = { template: '<viewFacility></viewFacility>' }
const PersonalTrainings = { template: '<personalTrainings></personalTrainings>' }
const GroupTrainings = { template: '<groupTrainings></groupTrainings>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Homepage},
		{ path: '/profile', component: Profile},
		{ path: '/viewFacility/:id', component: ViewFacility},
		{ path: '/trainings', component: Trainings},
		{ path: '/personalTrainings', component: PersonalTrainings},
		{ path: '/groupTrainings', component: GroupTrainings}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Trainer'
});