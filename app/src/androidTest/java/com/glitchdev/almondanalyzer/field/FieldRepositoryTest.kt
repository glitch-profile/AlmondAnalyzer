import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.glitchdev.almondanalyzer.core.data.db.AppDatabase
import com.glitchdev.almondanalyzer.fields.data.FieldDao
import com.glitchdev.almondanalyzer.fields.data.FieldRepositoryImpl
import com.glitchdev.almondanalyzer.fields.domain.Field
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNull
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FieldRepositoryTest {

    private lateinit var database: AppDatabase
    private lateinit var dao: FieldDao
    private lateinit var repository: FieldRepositoryImpl

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).build()
        dao = database.fieldDao()
        repository = FieldRepositoryImpl(dao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    @Test
    fun testAddAndGetField() = runBlocking {
        val field = Field(
            name = "Поле 1",
            variety = "Сорт A",
            cadastralNumber = "123-456",
            plantingScheme = "Схема 1",
            plantingYear = 2023,
            seedlingsCount = 100
        )

        val id = repository.addField(field)
        val savedField = repository.getFieldById(id)

        assertEquals(field.name, savedField?.name)
        assertEquals(field.seedlingsCount, savedField?.seedlingsCount)
    }

    @Test
    fun testDeleteFieldById() = runBlocking {
        val field = Field(
            name = "Поле для удаления",
            variety = "Сорт B",
            cadastralNumber = "789-012",
            plantingScheme = "Схема 2",
            plantingYear = 2024,
            seedlingsCount = 50
        )

        val id = repository.addField(field)
        repository.deleteFieldById(id)
        val deletedField = repository.getFieldById(id)
        assertNull(deletedField)
    }
}
