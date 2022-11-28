package basics

import generics.Response

class StudentController(
    private val studentRepository: StudentRepository,
    private val analyticsRepository: AnalyticsRepository
) {

    @GetMapping("/student/{id}")
    fun getStudent(@PathVariable id: Long): StudentAPI {
        val student = studentRepository.findStudent(id)

        if (student == null) throw ApiError(400, "No student found.")
        return StudentAPI(student.firstName, student.lastName)
    }

    @GetMapping("/student")
    fun getStudents(): List<StudentAPI> = studentRepository.getAllStudents().sortedBy { it.lastName }.map { StudentAPI(it.firstName, it.lastName) }

}

data class StudentAPI(
    val name: String,
    val surname: String
)

@Entity
data class StudentEntity(
    @Id @GeneratedValue
    val id: Long = -1,
    val firstName: String,
    val lastName: String
)

interface StudentRepository {

    fun findStudent(id: Long): StudentEntity?
    fun findStudentResult(id: Long): Response<StudentEntity, NotFoundException>
    fun getAllStudents(): List<StudentEntity>
}

object NotFoundException : Throwable()

interface AnalyticsRepository {

    fun getStudentByIdCount(id: Long): Int
    fun setStudentByIdCount(id: Long, count: Int)
}

data class ApiError(val code: Int, override val message: String) : Throwable(message)

annotation class Entity
annotation class Id
annotation class GeneratedValue
annotation class GetMapping(val path: String)
annotation class PathVariable
