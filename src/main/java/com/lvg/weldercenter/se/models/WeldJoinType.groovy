package com.lvg.weldercenter.se.models

/**
 * Created by Victor on 13.10.2017.
 *  НПАОП 0.00-1.16-96 Правила атестації зварників
 * При атестації слід враховувати тип зварного шва, вид і
 * умови виконання зварного з'єднання:
 * стиковий шов                                  - BW
 * кутовий шов                                   - FW
 * одностороннє зварне з'єднання                 - ss
 * двостороннє зварне з'єднання                  - bs
 * із підкладкою                                 - mb
 * без підкладки                                 - nb
 * із зачищенням кореня шва                      - gg
 * без зачищення кореня шва                      - ng
 * із присадним матеріалом                       - wm
 * без присадного матеріалу                      - nm
 */
enum WeldJoinType implements Serializable{

    BW(new WeldJoin(code: 'BW', description: 'стыковой шов')),
    FW(new WeldJoin(code: 'FW', description: 'угловой шов')),
    SS(new WeldJoin(code: 'ss', description: 'односторонний сварной шов')),
    BS(new WeldJoin(code: 'bs', description: 'двухсторонний сварной шов')),
    MB(new WeldJoin(code: 'mb', description: 'с подкладкой')),
    NB(new WeldJoin(code: 'nb', description: 'без подкладки')),
    GG(new WeldJoin(code: 'gg', description: 'с зачисткой корня шва')),
    NG(new WeldJoin(code: 'ng', description: 'без зачистки корня шва')),
    VM(new WeldJoin(code: 'vm', description: 'с присадочным материалом')),
    NM(new WeldJoin(code: 'nm', description: 'без присадочного материала'))

    WeldJoin value

    WeldJoinType(WeldJoin value){
        this.value = value
    }

    String toString(){
        return "$value.code"
    }


}