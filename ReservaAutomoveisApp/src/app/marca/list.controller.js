import swal from 'sweetalert2';

export default class ListController {
    
    constructor(MarcaServico, Notification) {    
        this._service = MarcaServico;
        this._notify = Notification;
        this.record = [];
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

    excluir(id) {
        swal({
            title             : 'Remover registro',
            text              : 'Deseja realmente remover o registro',
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

ListController.$inject = ['MarcaServico', 'Notification']
