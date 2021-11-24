package model.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

public class Employee implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private String cpf;
	private String job;
	private Integer salary;
	private String phone;
	private Date created_at;
	
	public Employee(Integer id,String name, String cpf, String job, Integer salary, String phone, Date created_at) {
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.job = job;
		this.salary = salary;
		this.phone = phone;
		this.created_at = created_at;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Date getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}

	@Override
	public String toString() {
		return "Funcionario [name=" + name + ", cpf=" + cpf + ", job=" + job + ", salary=" + salary
				+ ", created_at=" + created_at + "]";
	}
	
}
