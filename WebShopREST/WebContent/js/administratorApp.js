const ViewFacility = { template: '<view-facility></view-facility>' }
const Create = {template: '<create></create>'}
const Facility = {template: '<facillity></facillity>'}
const Comments = {template: '<comments></comments>'}
const CreateFacility = {template: '<create-facility></create-facility>'}
const UsersList = {template: '<users></users>'}
const PromoCodes = {template: '<promo-codes></promo-codes>'}
const Profile = { template: '<viewProfile></viewProfile>' }

const router = new VueRouter({
	mode: 'hash',
	  routes: [
		{ path: '/', name: 'home', component: ViewFacility},
		{ path: '/profile', component: Profile},
		{ path: '/create/:id', component: Create},
		{ path: '/facility/:id', component: Facility},
		{ path: '/comments', component: Comments},
		{ path: '/createFacility', component: CreateFacility},
		{ path: '/users', component: UsersList},
		{ path: '/promocodes', component: PromoCodes}
	  ]
});

var app = new Vue({
	router,
	el: '#Administrator'
});