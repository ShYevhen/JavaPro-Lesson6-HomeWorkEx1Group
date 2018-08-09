package net.ukr.shyevhen;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "Groups")
@NamedQuery(name = "Groups.getAll", query = "SELECT g FROM Group g")
public class Group {
	@Id
	@GeneratedValue
	private long id;
	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "myGroup", cascade = CascadeType.ALL)
	private List<Student> students = new ArrayList<>();

	public Group(String name) {
		super();
		this.name = name;
	}

	public Group() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}

	public List<Student> getStudents() {
		return Collections.unmodifiableList(students);
	}

	public void addStudent(Student st) {
		if (!students.contains(st)) {
			students.add(st);
			st.setMyGroup(this);
		}
	}

	@Override
	public String toString() {
		return "Group [id=" + id + ", name=" + name + "]";
	}

}
