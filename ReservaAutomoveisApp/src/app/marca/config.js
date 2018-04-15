import ListController from './list.controller'
import FormController from './form.controller'
import MarcaServico from './servico'

export const marcaConfig = (modulo) => {

  modulo.service('MarcaServico', MarcaServico)
  
  return ['$stateProvider', '$urlRouterProvider', 
   ($stateProvider, $urlRouterProvider) => {
    $stateProvider
      .state('marca', {
        template: require('@views/default.html'),
        url: '/marcas',
        redirectTo: 'marca.list'       
      })      
      .state('marca.list', {
        template: require('@views/marcas/list.html'),
        url: '/list',
        controller: ListController,
        controllerAs: 'vm'        
      })
      .state('marca.new', {
        template: require('@views/marcas/form.html'),
        url: '/new',
        controller: FormController,
        controllerAs: 'vm'
      })
      .state('marca.edit', {
        template: require('@views/marcas/form.html'),
        url: '/{id}',
        controller: FormController,
        controllerAs: 'vm'
      });
  }]
}
