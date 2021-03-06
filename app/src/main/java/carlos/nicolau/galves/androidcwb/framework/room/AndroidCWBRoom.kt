package carlos.nicolau.galves.androidcwb.framework.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class AndroidCWBRoom : RoomDatabase() {

    fun AndroidCWBRoom(){}

    abstract fun getUserDAO(): UserDAO

    companion object {
        @Volatile
        private var INSTANCE: AndroidCWBRoom? = null

        internal fun getDatabase(context: Context): AndroidCWBRoom {
            if (INSTANCE == null) {
                synchronized(AndroidCWBRoom::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AndroidCWBRoom::class.java, "database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE!!
        }
    }
}