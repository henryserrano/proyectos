package beans;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import clases.Persona;

@ManagedBean
@RequestScoped
public class personaBean {
	
	private Persona persona=new Persona();
	
	private static List<Persona> lstPersonas=new ArrayList();

	public List<Persona> getLstPersonas() {
		return lstPersonas;
	}

	public void setLstPersonas(List<Persona> lstPersonas) {
		this.lstPersonas = lstPersonas;
	}

	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	
	public void agregarPersona(){
		this.lstPersonas.add(this.persona);
	}

	public void eliminarPersona(Persona per){
		personaBean.lstPersonas.remove(per);
		
	}
}
