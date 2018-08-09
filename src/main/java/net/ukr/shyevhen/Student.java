package net.ukr.shyevhen;

import javax.persistence.*;

@Entity
@Table(name = "Students")
public class Student {
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private Integer number;
	@Column(nullable = false)
	private String name;
	@Column(nullable = false)
	private String surname;
	private Integer age;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private Group myGroup;

	public Student(Integer number, String name, String surname, Integer age) {
		super();
		this.number = number;
		this.name = name;
		this.surname = surname;
		this.age = age;
	}

	public Student() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Group getMyGroup() {
		return myGroup;
	}

	public void setMyGroup(Group myGroup) {
		this.myGroup = myGroup;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", number=" + number + ", name=" + name + ", surname=" + surname + ", age=" + age
				+ "]";
	}
}
