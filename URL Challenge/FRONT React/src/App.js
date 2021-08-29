import React, { Component } from 'react';
import logo from './logo.svg';
import './App.css';
import axios from "axios";
import "bootstrap/dist/css/bootstrap.min.css";
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { faEdit, faTrashAlt } from '@fortawesome/free-solid-svg-icons';
import { Modal, ModalBody, ModalFooter, ModalHeader } from 'reactstrap';


const url="http://127.0.0.1:8000/URL/";
const ENCODING_DECODING_WORD = "CREATING-SHORT-URL"

class App extends Component {
state={
  data:[],
  modalInsertar: false,
  modalEliminar: false,
  form:{
    id: '',
    created_at: '',
    clicks: '',
    original_url: '',
    short_url: '',
	updated_at: ''
  },
  	message: ''
}

peticionGet=()=>{
axios.get(url).then(response=>{
	console.log(response.data);
  this.setState({data: response.data});
}).catch(error=>{
	
  console.log(error.message);
})
}

peticionPost=async()=>{

 await axios.post(url,this.state.form).then(response=>{
    this.modalInsertar();
    this.peticionGet();
  }).catch(error=>{
	  
	  let msgValidationApi = error.response.data.detail[0].msg;
	  let msgValidationCustome = error.response.data.detail;
	  
	  let information = msgValidationApi ? msgValidationApi : msgValidationCustome
	  
	 this.setState({message: information});
	console.log(error.response.data.detail);
	console.log(error.response.data.detail[0].msg);
  })
}

peticionPut=()=>{

  axios.put(url, this.state.form).then(response=>{
    this.peticionGet();
	this.setState({modalActualizar: !this.state.modalActualizar});
  })
}

peticionPatch=(short_url)=>{
	
	let transform_url = short_url.replaceAll('/', ENCODING_DECODING_WORD)

	axios.patch(url+transform_url).then(response=>{
    this.peticionGet();
	
  })
}

peticionDelete=()=>{

    axios.delete(url+this.state.form.id).then(response=>{
    this.setState({modalEliminar: false});
    this.peticionGet();
  })
}

modalInsertar=()=>{
  this.setState({modalInsertar: !this.state.modalInsertar});
}

modalActualizar=()=>{
  this.setState({modalActualizar: !this.state.modalActualizar});
}


seleccionarEmpresa=(empresa)=>{
  this.setState({
    tipoModal: 'actualizar',
    form: {
      id: empresa.id,
	  created_at: empresa.created_at,
      clicks: empresa.clicks,
      original_url: empresa.original_url,
      short_url: empresa.short_url,
	  updated_at: empresa.updated_at
    }
  })
}

handleChange=async e=>{
e.persist();
await this.setState({
  form:{
    ...this.state.form,
    [e.target.name]: e.target.value
  }
});
console.log(this.state.form);
}

  componentDidMount() {
    this.peticionGet();
  }
  

  render(){
    const {form}=this.state;
	const {errores}=this.state;
  return (
    <div className="App">
    <br /><br /><br />
  <button className="btn btn-success" onClick={()=>{this.setState({form: null, tipoModal: 'insertar'}); this.modalInsertar()}}>Agregar URL</button>
  <br /><br />
    <table className="table ">
      <thead>

        <tr>
          <th>ID</th>
          <th>created_at</th>
          <th>clicks</th>
          <th>original_url</th>
		  <th>short_url</th>
          <th>updated_at</th>
          <th>Acciones</th>
        </tr>
      </thead>
      <tbody>
        {this.state.data.map(empresa=>{
          return(
		  
            <tr key= {empresa.id}>
          <td>{empresa.id}</td>
          <td>{empresa.created_at}</td>
          <td>{empresa.clicks}</td>
          <td>{empresa.original_url}</td>
          <td ><button className="btn btn-primary" onClick={()=>this.peticionPatch(empresa.short_url)}>{empresa.short_url}</button></td>
          <td>{empresa.updated_at}</td> 
 <td>
                <button className="btn btn-primary" onClick={()=>{this.seleccionarEmpresa(empresa); this.modalActualizar()}}><FontAwesomeIcon icon={faEdit}/></button>
                {"   "}
                <button className="btn btn-danger" onClick={()=>{this.seleccionarEmpresa(empresa); this.setState({modalEliminar: true})}}><FontAwesomeIcon icon={faTrashAlt}/></button>
                </td>
          </tr>
          )
        })}
      </tbody>
    </table>



    <Modal isOpen={this.state.modalInsertar}>  
	  
                <ModalHeader style={{display: 'block'}}>
				<div>	
				{this.state.message}								
				</div>			
				
                  <span style={{float: 'right'}} onClick={()=>this.modalInsertar()}>x</span>
                </ModalHeader>
                <ModalBody>
                  <div className="form-group">
                    <label htmlFor="capital_bursatil">original_url</label>
                    <input className="form-control" type="text" name="original_url" id="capital_bursatil" onChange={this.handleChange} value={form?form.original_url:''}/>
                  </div>
                </ModalBody>

                <ModalFooter>
                  {this.state.tipoModal==='insertar'?
                    <button className="btn btn-success" onClick={()=>this.peticionPost()}>
                    Insertar
                  </button>: <button className="btn btn-primary" onClick={()=>this.peticionPut()}>
                    xx
                  </button>
  }
                    <button className="btn btn-danger" onClick={()=>this.modalInsertar()}>Cancelar</button>
                </ModalFooter>
          </Modal>

<Modal isOpen={this.state.modalActualizar}>  
	  
                <ModalHeader style={{display: 'block'}}>
                  <span style={{float: 'right'}} onClick={()=>this.modalActualizar()}>x</span>
                </ModalHeader>
                <ModalBody>
                  <div className="form-group">
                    <label htmlFor="id">ID</label>
                    <input className="form-control" type="text" name="id" id="id" readOnly onChange={this.handleChange} value={form?form.id: this.state.data.length+1}/>
                    <br />
                    <label htmlFor="nombre">created_at</label>
                    <input className="form-control" type="text" name="nombre" id="nombre" readOnly onChange={this.handleChange} value={form?form.created_at: ''}/>
                    <br />
                    <label htmlFor="nombre">clicks</label>
                    <input className="form-control" type="text" name="pais" id="pais" readOnly onChange={this.handleChange} value={form?form.clicks: ''}/>
                    <br />
                    <label htmlFor="capital_bursatil">original_url</label>
                    <input className="form-control" type="text" name="original_url" id="capital_bursatil" readOnly onChange={this.handleChange} value={form?form.original_url:''}/>
					
					 <br />
                    <label htmlFor="short_url" >short_url</label>
                    <input className="form-control" type="text" name="short_url" id="short_url" readOnly onChange={this.handleChange} value={form?form.short_url: ''}/>


                    <br />
                    <label htmlFor="updated_at">updated_at</label>
                    <input className="form-control" type="text" name="updated_at" id="updated_at" readOnly onChange={this.handleChange} value={form?form.updated_at:''}/>
                  </div>
                </ModalBody>

                <ModalFooter>

                    <button className="btn btn-success" onClick={()=>this.peticionPut()}>
                    Click in web

                  </button>
  
                    <button className="btn btn-danger" onClick={()=>this.modalActualizar()}>Cancelar</button>
                </ModalFooter>
          </Modal>
		  

          <Modal isOpen={this.state.modalEliminar}>
            <ModalBody>
               Estás seguro que deseas eliminar a la empresa {form && form.nombre}
            </ModalBody>
            <ModalFooter>
              <button className="btn btn-danger" onClick={()=>this.peticionDelete()}>Sí</button>
              <button className="btn btn-secundary" onClick={()=>this.setState({modalEliminar: false})}>No</button>
            </ModalFooter>
          </Modal>
  </div>



  );
}
}
export default App;