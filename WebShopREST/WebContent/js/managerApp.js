const Comments = {template: '<comments></comments>'}
const Facility = {template: '<facilitym></facilitym>'}
const Training = {template: '<create-training></create-training>'}
const Update = {template: '<update-training></update-training>'}

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: Comments},
		{ path: '/facility', component: Facility},
		{ path: '/training', component: Training},
		{ path: '/update/:id', component: Update}
	  ]
});

var appLogin = new Vue({
	router,
	el: '#Manager'
});