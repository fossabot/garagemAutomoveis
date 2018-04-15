import swal from 'sweetalert2';

export default class ListController {
    
    constructor(AutomovelServico, Notification) {    
        this._service = AutomovelServico;
        this._notify = Notification;
        this.record = {};
        this.reserva = {}
        this.load();
    }

    load() {
        this._service.findAll()
          .then(data => {              
              this.record = data;
          })
          .catch(error => {
              console.log(error)
          })                   
    }

    liberar(obj, value) {
        let _obj = angular.copy(obj);
        
        swal({
            title             : 'Liberar automóvel',
            text              : 'Deseja realmente liberar o automóvel?',
            type              : 'warning',
            showConfirmButton : true,
            showCancelButton  : true,
            confirmButtonText : 'Sim!',
            cancelButtonText  : 'Não obrigado'
        }).then(resp => {    
            _obj.disponibilidade = 'DISPONIVEL';        
            return resp.value ? this._service.save(_obj) : Promise.reject(
                  {
                      type: 'warning', 
                      message: 'Operação cancelada!!!'
                  })
        }).then(response => {   
            console.log(response)                     
            if(response.status == 200) {
                swal(
                    'Liberado!',
                    'O automóvel foi liberado.',
                    'success'
                  )
            }else{
                swal(
                    'Algo não ocorreu como esperado.!',
                    `Ocorreram problemas ao liberar o automóvel.
                    ${response.data.rootMessage}`,
                    'success'
                  )
            }
            this.load()            
        }).catch(erro => {
            this._notify(
                {
                    message: erro.message || 'Problemas ao liberar o automóvel'
                },
                 erro.type || 'error'
                )
        })
    }      
    
    
    excluir(id) {
        swal({
            title             : 'Remover registro',
            text              : 'Deseja realmente remover o registro?',
            type              : 'warning',
            showConfirmButton : true,
            showCancelButton  : true,
            confirmButtonText : 'Sim!',
            cancelButtonText  : 'Não obrigado'
        }).then(resp => {
            return resp.value ? this._service.remove(id) : Promise.reject(
                  {
                      type: 'warning', 
                      message: 'Operação cancelada!!!'
                  })
        }).then(response => {
            this.load()
            this._notify.success('Registro excluído com sucesso')
        }).catch(erro => {
            this._notify(
                {
                    message: erro.message || 'Problemas ao excluir o registro'
                },
                 erro.type || 'error'
                )
        })
    }
}

ListController.$inject = ['AutomovelServico', 'Notification']
