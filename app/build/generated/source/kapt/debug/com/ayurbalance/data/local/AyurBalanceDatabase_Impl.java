package com.ayurbalance.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AyurBalanceDatabase_Impl extends AyurBalanceDatabase {
  private volatile MealPlanDao _mealPlanDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `meal_plan_cache` (`dateKey` TEXT NOT NULL, `mealsJson` TEXT NOT NULL, `ritu` TEXT NOT NULL, `prakriti` TEXT NOT NULL, `generatedAt` INTEGER NOT NULL, PRIMARY KEY(`dateKey`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'e0dd480f7e358c6b5fc3139c15c89a8b')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `meal_plan_cache`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsMealPlanCache = new HashMap<String, TableInfo.Column>(5);
        _columnsMealPlanCache.put("dateKey", new TableInfo.Column("dateKey", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealPlanCache.put("mealsJson", new TableInfo.Column("mealsJson", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealPlanCache.put("ritu", new TableInfo.Column("ritu", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealPlanCache.put("prakriti", new TableInfo.Column("prakriti", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsMealPlanCache.put("generatedAt", new TableInfo.Column("generatedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysMealPlanCache = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesMealPlanCache = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoMealPlanCache = new TableInfo("meal_plan_cache", _columnsMealPlanCache, _foreignKeysMealPlanCache, _indicesMealPlanCache);
        final TableInfo _existingMealPlanCache = TableInfo.read(db, "meal_plan_cache");
        if (!_infoMealPlanCache.equals(_existingMealPlanCache)) {
          return new RoomOpenHelper.ValidationResult(false, "meal_plan_cache(com.ayurbalance.data.local.CachedMealPlanEntity).\n"
                  + " Expected:\n" + _infoMealPlanCache + "\n"
                  + " Found:\n" + _existingMealPlanCache);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "e0dd480f7e358c6b5fc3139c15c89a8b", "945caf7de01eaefa61881885925e9056");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "meal_plan_cache");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `meal_plan_cache`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(MealPlanDao.class, MealPlanDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public MealPlanDao mealPlanDao() {
    if (_mealPlanDao != null) {
      return _mealPlanDao;
    } else {
      synchronized(this) {
        if(_mealPlanDao == null) {
          _mealPlanDao = new MealPlanDao_Impl(this);
        }
        return _mealPlanDao;
      }
    }
  }
}
