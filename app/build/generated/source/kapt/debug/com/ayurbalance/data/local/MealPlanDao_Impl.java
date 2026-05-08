package com.ayurbalance.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MealPlanDao_Impl implements MealPlanDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CachedMealPlanEntity> __insertionAdapterOfCachedMealPlanEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStale;

  public MealPlanDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCachedMealPlanEntity = new EntityInsertionAdapter<CachedMealPlanEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `meal_plan_cache` (`dateKey`,`mealsJson`,`ritu`,`prakriti`,`generatedAt`) VALUES (?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CachedMealPlanEntity entity) {
        if (entity.getDateKey() == null) {
          statement.bindNull(1);
        } else {
          statement.bindString(1, entity.getDateKey());
        }
        if (entity.getMealsJson() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getMealsJson());
        }
        if (entity.getRitu() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getRitu());
        }
        if (entity.getPrakriti() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getPrakriti());
        }
        statement.bindLong(5, entity.getGeneratedAt());
      }
    };
    this.__preparedStmtOfDeleteStale = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM meal_plan_cache WHERE generatedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertPlan(final CachedMealPlanEntity plan,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCachedMealPlanEntity.insert(plan);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStale(final long olderThanMs, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStale.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, olderThanMs);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteStale.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getPlanForDate(final String dateKey,
      final Continuation<? super CachedMealPlanEntity> $completion) {
    final String _sql = "SELECT * FROM meal_plan_cache WHERE dateKey = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (dateKey == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, dateKey);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CachedMealPlanEntity>() {
      @Override
      @Nullable
      public CachedMealPlanEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDateKey = CursorUtil.getColumnIndexOrThrow(_cursor, "dateKey");
          final int _cursorIndexOfMealsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "mealsJson");
          final int _cursorIndexOfRitu = CursorUtil.getColumnIndexOrThrow(_cursor, "ritu");
          final int _cursorIndexOfPrakriti = CursorUtil.getColumnIndexOrThrow(_cursor, "prakriti");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generatedAt");
          final CachedMealPlanEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpDateKey;
            if (_cursor.isNull(_cursorIndexOfDateKey)) {
              _tmpDateKey = null;
            } else {
              _tmpDateKey = _cursor.getString(_cursorIndexOfDateKey);
            }
            final String _tmpMealsJson;
            if (_cursor.isNull(_cursorIndexOfMealsJson)) {
              _tmpMealsJson = null;
            } else {
              _tmpMealsJson = _cursor.getString(_cursorIndexOfMealsJson);
            }
            final String _tmpRitu;
            if (_cursor.isNull(_cursorIndexOfRitu)) {
              _tmpRitu = null;
            } else {
              _tmpRitu = _cursor.getString(_cursorIndexOfRitu);
            }
            final String _tmpPrakriti;
            if (_cursor.isNull(_cursorIndexOfPrakriti)) {
              _tmpPrakriti = null;
            } else {
              _tmpPrakriti = _cursor.getString(_cursorIndexOfPrakriti);
            }
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _result = new CachedMealPlanEntity(_tmpDateKey,_tmpMealsJson,_tmpRitu,_tmpPrakriti,_tmpGeneratedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getPlansForRange(final String from, final String to,
      final Continuation<? super List<CachedMealPlanEntity>> $completion) {
    final String _sql = "SELECT * FROM meal_plan_cache WHERE dateKey BETWEEN ? AND ? ORDER BY dateKey";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (from == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, from);
    }
    _argIndex = 2;
    if (to == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, to);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CachedMealPlanEntity>>() {
      @Override
      @NonNull
      public List<CachedMealPlanEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfDateKey = CursorUtil.getColumnIndexOrThrow(_cursor, "dateKey");
          final int _cursorIndexOfMealsJson = CursorUtil.getColumnIndexOrThrow(_cursor, "mealsJson");
          final int _cursorIndexOfRitu = CursorUtil.getColumnIndexOrThrow(_cursor, "ritu");
          final int _cursorIndexOfPrakriti = CursorUtil.getColumnIndexOrThrow(_cursor, "prakriti");
          final int _cursorIndexOfGeneratedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "generatedAt");
          final List<CachedMealPlanEntity> _result = new ArrayList<CachedMealPlanEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CachedMealPlanEntity _item;
            final String _tmpDateKey;
            if (_cursor.isNull(_cursorIndexOfDateKey)) {
              _tmpDateKey = null;
            } else {
              _tmpDateKey = _cursor.getString(_cursorIndexOfDateKey);
            }
            final String _tmpMealsJson;
            if (_cursor.isNull(_cursorIndexOfMealsJson)) {
              _tmpMealsJson = null;
            } else {
              _tmpMealsJson = _cursor.getString(_cursorIndexOfMealsJson);
            }
            final String _tmpRitu;
            if (_cursor.isNull(_cursorIndexOfRitu)) {
              _tmpRitu = null;
            } else {
              _tmpRitu = _cursor.getString(_cursorIndexOfRitu);
            }
            final String _tmpPrakriti;
            if (_cursor.isNull(_cursorIndexOfPrakriti)) {
              _tmpPrakriti = null;
            } else {
              _tmpPrakriti = _cursor.getString(_cursorIndexOfPrakriti);
            }
            final long _tmpGeneratedAt;
            _tmpGeneratedAt = _cursor.getLong(_cursorIndexOfGeneratedAt);
            _item = new CachedMealPlanEntity(_tmpDateKey,_tmpMealsJson,_tmpRitu,_tmpPrakriti,_tmpGeneratedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
