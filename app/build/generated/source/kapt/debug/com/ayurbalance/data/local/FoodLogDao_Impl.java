package com.ayurbalance.data.local;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
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
public final class FoodLogDao_Impl implements FoodLogDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<FoodLogEntity> __insertionAdapterOfFoodLogEntity;

  public FoodLogDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfFoodLogEntity = new EntityInsertionAdapter<FoodLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `food_logs_local` (`id`,`foodName`,`mealType`,`calories`,`proteinG`,`carbsG`,`fatG`,`doshaTag`,`createdAt`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final FoodLogEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getFoodName() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getFoodName());
        }
        if (entity.getMealType() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getMealType());
        }
        statement.bindLong(4, entity.getCalories());
        statement.bindDouble(5, entity.getProteinG());
        statement.bindDouble(6, entity.getCarbsG());
        statement.bindDouble(7, entity.getFatG());
        if (entity.getDoshaTag() == null) {
          statement.bindNull(8);
        } else {
          statement.bindString(8, entity.getDoshaTag());
        }
        statement.bindLong(9, entity.getCreatedAt());
      }
    };
  }

  @Override
  public Object insert(final FoodLogEntity entity, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfFoodLogEntity.insert(entity);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object getSince(final long since,
      final Continuation<? super List<FoodLogEntity>> $completion) {
    final String _sql = "SELECT * FROM food_logs_local WHERE createdAt >= ? ORDER BY createdAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<FoodLogEntity>>() {
      @Override
      @NonNull
      public List<FoodLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfFoodName = CursorUtil.getColumnIndexOrThrow(_cursor, "foodName");
          final int _cursorIndexOfMealType = CursorUtil.getColumnIndexOrThrow(_cursor, "mealType");
          final int _cursorIndexOfCalories = CursorUtil.getColumnIndexOrThrow(_cursor, "calories");
          final int _cursorIndexOfProteinG = CursorUtil.getColumnIndexOrThrow(_cursor, "proteinG");
          final int _cursorIndexOfCarbsG = CursorUtil.getColumnIndexOrThrow(_cursor, "carbsG");
          final int _cursorIndexOfFatG = CursorUtil.getColumnIndexOrThrow(_cursor, "fatG");
          final int _cursorIndexOfDoshaTag = CursorUtil.getColumnIndexOrThrow(_cursor, "doshaTag");
          final int _cursorIndexOfCreatedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "createdAt");
          final List<FoodLogEntity> _result = new ArrayList<FoodLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final FoodLogEntity _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpFoodName;
            if (_cursor.isNull(_cursorIndexOfFoodName)) {
              _tmpFoodName = null;
            } else {
              _tmpFoodName = _cursor.getString(_cursorIndexOfFoodName);
            }
            final String _tmpMealType;
            if (_cursor.isNull(_cursorIndexOfMealType)) {
              _tmpMealType = null;
            } else {
              _tmpMealType = _cursor.getString(_cursorIndexOfMealType);
            }
            final int _tmpCalories;
            _tmpCalories = _cursor.getInt(_cursorIndexOfCalories);
            final float _tmpProteinG;
            _tmpProteinG = _cursor.getFloat(_cursorIndexOfProteinG);
            final float _tmpCarbsG;
            _tmpCarbsG = _cursor.getFloat(_cursorIndexOfCarbsG);
            final float _tmpFatG;
            _tmpFatG = _cursor.getFloat(_cursorIndexOfFatG);
            final String _tmpDoshaTag;
            if (_cursor.isNull(_cursorIndexOfDoshaTag)) {
              _tmpDoshaTag = null;
            } else {
              _tmpDoshaTag = _cursor.getString(_cursorIndexOfDoshaTag);
            }
            final long _tmpCreatedAt;
            _tmpCreatedAt = _cursor.getLong(_cursorIndexOfCreatedAt);
            _item = new FoodLogEntity(_tmpId,_tmpFoodName,_tmpMealType,_tmpCalories,_tmpProteinG,_tmpCarbsG,_tmpFatG,_tmpDoshaTag,_tmpCreatedAt);
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

  @Override
  public Object totalCaloriesSince(final long since,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT SUM(calories) FROM food_logs_local WHERE createdAt >= ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, since);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @Nullable
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
