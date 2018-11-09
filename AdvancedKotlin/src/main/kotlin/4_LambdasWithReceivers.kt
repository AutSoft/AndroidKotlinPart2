interface Database {

    fun beginTransaction()

    fun commitTransaction()

    fun rollbackTransaction()

    // Can throw an IllegalStateException if it already has the item we're trying to add
    fun insert(item: Int)

    // Can throw an IllegalStateException if it doesn't have the item we're trying to delete
    fun delete(item: Int)

}


fun databaseDemo(db: Database) {

    db.beginTransaction()
    try {
        db.insert(11)
        db.delete(12)
        db.commitTransaction()
    } catch (e: IllegalStateException) {
        db.rollbackTransaction()
    }

}

//////////////////////////////////////////////////////////////////////////////////////

fun inTransaction(db: Database, block: () -> Unit) {

    db.beginTransaction()
    try {
        block()
        db.commitTransaction()
    } catch (e: IllegalStateException) {
        db.rollbackTransaction()
    }

}

fun useDatabase(db: Database) {

    inTransaction(db) {
        db.delete(11)
        db.insert(12)
    }

}

//////////////////////////////////////////////////////////////////////////////////////

fun Database.inTransaction2(block: () -> Unit) {

    this.beginTransaction()
    try {
        block()
        this.commitTransaction()
    } catch (e: IllegalStateException) {
        this.rollbackTransaction()
    }

}

fun useDatabase2(db: Database) {

    db.inTransaction2 {
        db.delete(11)
        db.insert(12)
    }

}

//////////////////////////////////////////////////////////////////////////////////////

fun Database.inTransaction3(block: (Database) -> Unit) {

    beginTransaction()
    try {
        block(this)
        commitTransaction()
    } catch (e: IllegalStateException) {
        rollbackTransaction()
    }

}

fun useDatabase3(db: Database) {

    db.inTransaction3 {
        it.delete(11)
        it.insert(12)
    }

}

//////////////////////////////////////////////////////////////////////////////////////

fun Database.inTransaction4(block: Database.() -> Unit) {

    beginTransaction()
    try {
        block()
        commitTransaction()
    } catch (e: IllegalStateException) {
        rollbackTransaction()
    }

}

fun useDatabase4(db: Database) {

    db.inTransaction4 {
        delete(11)
        insert(12)
    }

}

//////////////////////////////////////////////////////////////////////////////////////

inline fun Database.inTransaction5(block: Database.() -> Unit) {

    beginTransaction()
    try {
        block()
        commitTransaction()
    } catch (e: IllegalStateException) {
        rollbackTransaction()
    }

}

fun useDatabase5(db: Database) {

    db.inTransaction5 {
        delete(11)
        insert(12)
    }

}
