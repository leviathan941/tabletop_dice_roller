/*
 * Tabletop Dice Roller
 * Copyright (C) 2023 Alexey Kuzin
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.leviathan941.tabletopdiceroller.model.dice.tree.expandable

import kotlinx.coroutines.flow.MutableStateFlow
import org.leviathan941.tabletopdiceroller.model.dice.internal.tree.DieResultNode
import org.leviathan941.tabletopdiceroller.model.dice.tree.Node
import org.leviathan941.tabletopdiceroller.model.dice.tree.Root
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResult
import org.leviathan941.tabletopdiceroller.model.dice.tree.result.DieResultTree

class DieExpandableTree(
    resultTree: DieResultTree,
    previouslyExpandedNodes: Set<ExpandableNode<DieResult>>,
) {
    private val _root = resultTree.root.children.map { node ->
        ExpandableDieResultNode(node, previouslyExpandedNodes)
    }
    val root: Root<ExpandableNode<DieResult>> = ExpandableDieResultRoot(_root)

    fun expandedNodes(): Set<ExpandableNode<DieResult>> {
        return mutableSetOf<ExpandableNode<DieResult>>().apply {
            _root.forEach { addExpandedNodes(it, this) }
        }
    }

    private fun addExpandedNodes(
        node: ExpandableNode<DieResult>,
        result: MutableSet<ExpandableNode<DieResult>>,
    ) {
        if (node.isExpanded.value) {
            result.add(node)
            node.children.forEach { addExpandedNodes(it, result) }
        }
    }

    companion object {
        val Empty get() = DieExpandableTree(DieResultTree(), emptySet())
    }
}

private class ExpandableDieResultRoot(
    private val _children: List<ExpandableNode<DieResult>>
) : Root<ExpandableNode<DieResult>> {
    override val children: List<ExpandableNode<DieResult>>
        get() = _children.sorted()
}

private class ExpandableDieResultNode(
    private val resultNode: Node<DieResult>,
    previouslyExpanded: Set<ExpandableNode<DieResult>>,
) : ExpandableNode<DieResult> {

    override val isExpanded = MutableStateFlow(
        isExpandable && previouslyExpanded.any { it.isCompatibleWith(this) }
    )

    override val isExpandable: Boolean get() =
        resultNode is DieResultNode && resultNode.isExpandable
    override val results: List<DieResult> get() = resultNode.results

    override val children: List<ExpandableDieResultNode> =
        mutableListOf<ExpandableDieResultNode>().apply {
            resultNode.children.forEach { node ->
                add(ExpandableDieResultNode(node, previouslyExpanded))
            }
        }.sorted()

    override val order: Int get() = resultNode.order

    override fun isCompatibleWith(other: Node<DieResult>): Boolean =
        other is ExpandableDieResultNode && other.resultNode.isCompatibleWith(this.resultNode)

    override fun toString(): String {
        return "ExpandableDieResultNode(resultNode=$resultNode, isExpanded=$isExpanded)"
    }

    override fun compareTo(other: ExpandableNode<DieResult>): Int =
        order.compareTo(other.order)
}
