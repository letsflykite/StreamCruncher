/*
 * StreamCruncher:  Copyright (c) 2006-2008, Ashwin Jayaprakash. All Rights Reserved.
 * Contact:         ashwin {dot} jayaprakash {at} gmail {dot} com
 * Web:             http://www.StreamCruncher.com
 * 
 * This file is part of StreamCruncher.
 * 
 *     StreamCruncher is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU Lesser General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     StreamCruncher is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU Lesser General Public License for more details.
 * 
 *     You should have received a copy of the GNU Lesser General Public License
 *     along with StreamCruncher. If not, see <http://www.gnu.org/licenses/>.
 */
package streamcruncher.innards.core.partition;

import java.io.ObjectStreamException;

import streamcruncher.api.artifact.RowSpec;
import streamcruncher.api.artifact.TableFQN;
import streamcruncher.innards.core.filter.FilteredTable;

/*
 * Author: Ashwin Jayaprakash Date: Feb 12, 2006 Time: 2:19:32 PM
 */

public class PartitionedTable extends FilteredTable {
    private static final long serialVersionUID = 1L;

    /**
     * @param queryName
     * @param realTableFQN
     * @param realTableRowSpec
     * @param finalTableFQN
     * @param partitionSpec
     */
    public PartitionedTable(String queryName, TableFQN realTableFQN, RowSpec realTableRowSpec,
            TableFQN finalTableFQN, PartitionSpec partitionSpec) {
        this(queryName, realTableFQN, realTableRowSpec, finalTableFQN,
                StreamReaderTableWriterPartitioner.class.getName(), partitionSpec);
    }

    /**
     * Used for Serialization.
     * 
     * @param queryName
     * @param realTableFQN
     * @param realTableRowSpec
     * @param finalTableFQN
     * @param filterClassFQN
     * @param partitionSpec
     */
    protected PartitionedTable(String queryName, TableFQN realTableFQN, RowSpec realTableRowSpec,
            TableFQN finalTableFQN, String filterClassFQN, PartitionSpec partitionSpec) {
        super(queryName, realTableFQN, realTableRowSpec, finalTableFQN, filterClassFQN,
                partitionSpec);
    }

    @Override
    protected Object writeReplace() throws ObjectStreamException {
        return new PartitionedTable(queryName, sourceTableFQN, sourceTableRowSpec, finalTableFQN,
                tableFilterClassName, (PartitionSpec) filterSpec);
    }
}
