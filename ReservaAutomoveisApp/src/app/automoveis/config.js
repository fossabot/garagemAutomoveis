import ListController from './list.controller'
import FormController from './form.controller'
import AutomovelServico from './servico'

export const automovelConfig = (modulo) => {

  modulo.service('AutomovelServico', AutomovelServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('automovel', {
        template: require('@views/default.html'),
        url: '/automoveis',
        redirectTo: 'automovel.list'       
      })      
      .state('automovel.list', {
        template: require('@views/automoveis/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'        
      })
      .state('automovel.new', {
        template: require('@views/automoveis/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('automovel.edit', {
        template: require('@views/automoveis/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('automovel.reserva', {
        template: require('@views/automoveis/reserva.html'),
        url: '/reserva/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}
