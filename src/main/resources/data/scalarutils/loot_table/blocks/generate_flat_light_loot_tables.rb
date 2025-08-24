require 'json'

colors = %w[
  white light_gray gray black brown red orange yellow lime green
  cyan light_blue blue purple magenta pink
]

colors.each do |color|
  # Normal loot table
  loot_table = {
    type: "minecraft:block",
    pools: [
      {
        bonus_rolls: 0.0,
        conditions: [
          { condition: "minecraft:survives_explosion" }
        ],
        entries: [
          {
            type: "minecraft:item",
            name: "scalarutils:#{color}_flat_light"
          }
        ],
        rolls: 1.0
      }
    ],
    random_sequence: "scalarutils:blocks/#{color}_flat_light"
  }
  File.write("#{color}_flat_light.json", JSON.pretty_generate(loot_table))

  # Inverted loot table
  inverted_loot_table = {
    type: "minecraft:block",
    pools: [
      {
        bonus_rolls: 0.0,
        conditions: [
          { condition: "minecraft:survives_explosion" }
        ],
        entries: [
          {
            type: "minecraft:item",
            name: "scalarutils:inverted_#{color}_flat_light"
          }
        ],
        rolls: 1.0
      }
    ],
    random_sequence: "scalarutils:blocks/inverted_#{color}_flat_light"
  }
  File.write("inverted_#{color}_flat_light.json", JSON.pretty_generate(inverted_loot_table))
end